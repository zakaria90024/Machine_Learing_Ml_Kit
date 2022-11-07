

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.sasoftbd.machine_learing_ml_kit.zone_Panel.approvedFragment.approveFragment
import com.sasoftbd.machine_learing_ml_kit.zone_Panel.billFragment.billFragment
import com.sasoftbd.machine_learing_ml_kit.zone_Panel.orderFragment.orderFragment
import com.sasoftbd.machine_learing_ml_kit.zone_Panel.returnFragement.returnFragment
import com.sasoftbd.machine_learing_ml_kit.zone_Panel.zhAppFragment.zhAppFragment


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return orderFragment()
            }
            1 -> {
                return approveFragment()
            }
            2 -> {
                return returnFragment()
            }
            3 -> {
                return billFragment()
            }
            4 -> {
                return zhAppFragment()
            }
            else -> {
                return orderFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "New Order"
            }
            1 -> {
                return "ZH Approved"
            }
            2 -> {
                return "Return Order"
            }
            3 -> {
                return "Bill Done"
            }
            4 -> {
                return "ZH App List"
            }
        }
        return super.getPageTitle(position)
    }

}