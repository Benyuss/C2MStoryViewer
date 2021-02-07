package com.c2m.storyviewer.customview

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.c2m.storyviewer.data.StoryUser
import com.c2m.storyviewer.screen.StoryDisplayFragment

class StoryPager2Adapter constructor(
    fragmentManager: AppCompatActivity,
    private val storyList: ArrayList<StoryUser>
) : FragmentStateAdapter(fragmentManager) {
    //private val fragments: MutableList<Fragment> = mutableListOf()

    val fragmentsMap: HashMap<Int, Fragment> = HashMap()
    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = StoryDisplayFragment.newInstance(position, storyList[position])
        //fragments.add(fragment)
        fragmentsMap[position] = fragment
        return fragment
    }
}