package com.sahu.dinningroom.ui.ingredient

import android.os.Bundle
import com.sahu.dinningroom.R
import com.sahu.dinningroom.appUtil.ui.BaseFragment
import com.sahu.dinningroom.databinding.IngredientFragmentBinding

class IngredientsFragment
    : BaseFragment<IngredientFragmentBinding>(R.layout.ingredient_fragment){

    companion object {
        fun newInstance() = IngredientsFragment()
    }

    private lateinit var binding: IngredientFragmentBinding

    override fun init(savedInstanceState: Bundle?, binding: IngredientFragmentBinding) {
        this.binding = binding
    }
}