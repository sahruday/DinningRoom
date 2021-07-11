package com.sahu.dinningroom.ui.ingredient

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.sahu.dinningroom.R
import com.sahu.dinningroom.appUtil.ui.BaseFragment
import com.sahu.dinningroom.dataHolders.MenuItem
import com.sahu.dinningroom.databinding.IngredientFragmentBinding
import com.sahu.dinningroom.ui.AppViewModel
import com.sahu.dinningroom.ui.ingredient.adapter.IngredientsAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class IngredientsFragment
    : BaseFragment<IngredientFragmentBinding>(R.layout.ingredient_fragment){

    companion object {
        fun newInstance() = IngredientsFragment()
    }

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: IngredientFragmentBinding

    private val ingredientsAdapter = IngredientsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if(savedInstanceState == null) {
            viewModel.searchString.postValue("")
            viewModel.selectedCategory.postValue(0)
        }
    }

    override fun init(savedInstanceState: Bundle?, binding: IngredientFragmentBinding) {
        this.binding = binding
        binding.recycle.adapter = ingredientsAdapter

        lifecycleScope.launchWhenResumed {
            viewModel.getMenuItems().collect { render(it) }
        }

        //SearchTextListener
        binding.searchTxt.addTextChangedListener {
            viewModel.searchInIngredients(it.toString())
            viewModel.searchString.postValue(it.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers(){
        viewModel.searchString.observe(viewLifecycleOwner, { updateList(viewModel.selectedCategory.value ?: 0, it) })

        viewModel.selectedCategory.observe(viewLifecycleOwner, { updateList(it, viewModel.searchString.value ?: "")})
    }

    private fun render(list: List<MenuItem>){
        if(list.isNullOrEmpty())
            binding.categoryVP.visibility = View.GONE
        else {
            binding.categoryVP.visibility = View.VISIBLE

            val categorySet = hashSetOf<Int>()
            list.forEach {  categorySet.add(it.menu.category) }

            var tab = binding.categoryVP.newTab()
            tab.text = resources.getString(R.string.all)
            tab.tag = 0
            binding.categoryVP.addTab(tab)
            if(viewModel.selectedCategory.value == 0)
                tab.select()

            for(category in categorySet) {
                tab = binding.categoryVP.newTab()
                tab.text = resources.getString(R.string.category, category.toString())
                tab.tag = category
                binding.categoryVP.addTab(tab)
                if(viewModel.selectedCategory.value == category)
                    tab.select()
            }

            binding.categoryVP.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab) =
                    viewModel.selectedCategory.postValue(tab.tag as Int)

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

        }
        ingredientsAdapter.submitList(list)
    }

    private fun updateList(category: Int, searchString: String){
        lifecycleScope.launch {
            ingredientsAdapter.submitList(viewModel.availableItems.first()
                .filter { it.menu.Name.contains(searchString, true) }
                .filter { if(category == 0 ) true else it.menu.category == category }
            )
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val showIngredientsMenuItem: android.view.MenuItem? = menu.findItem(R.id.showIngredients)
        showIngredientsMenuItem?.isVisible = false
    }

}