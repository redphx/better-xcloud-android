package com.betterxcloud.android.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.betterxcloud.android.R

/**
 * Main TV launcher activity with Leanback browse interface
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, MainFragment())
                .commit()
        }

        Log.i(TAG, "MainActivity created")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

/**
 * Main browse fragment for TV interface
 */
class MainFragment : BrowseSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
        loadRows()
        setupEventListeners()
    }

    private fun setupUI() {
        title = getString(R.string.app_name)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // Set brand color
        brandColor = ContextCompat.getColor(requireContext(), R.color.xbox_green)

        // Set search icon color
        searchAffordanceColor = ContextCompat.getColor(requireContext(), R.color.white)
    }

    private fun loadRows() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // Quick Launch Row
        val quickLaunchRow = createQuickLaunchRow()
        rowsAdapter.add(quickLaunchRow)

        // Settings Row
        val settingsRow = createSettingsRow()
        rowsAdapter.add(settingsRow)

        adapter = rowsAdapter
    }

    private fun createQuickLaunchRow(): ListRow {
        val header = HeaderItem(0, "Quick Launch")
        val listRowAdapter = ArrayObjectAdapter(CardPresenter())

        // Launch xCloud button
        listRowAdapter.add(MenuItem(
            id = 1,
            title = "Launch xCloud",
            description = "Start Xbox Cloud Gaming",
            iconResId = R.drawable.ic_xbox
        ))

        return ListRow(header, listRowAdapter)
    }

    private fun createSettingsRow(): ListRow {
        val header = HeaderItem(1, "Settings")
        val listRowAdapter = ArrayObjectAdapter(CardPresenter())

        // Settings button
        listRowAdapter.add(MenuItem(
            id = 2,
            title = "Settings",
            description = "Configure Better xCloud",
            iconResId = R.drawable.ic_settings
        ))

        // About button
        listRowAdapter.add(MenuItem(
            id = 3,
            title = "About",
            description = "Version and information",
            iconResId = R.drawable.ic_info
        ))

        return ListRow(header, listRowAdapter)
    }

    private fun setupEventListeners() {
        onItemViewClickedListener = OnItemViewClickedListener { _, item, _, _ ->
            if (item is MenuItem) {
                handleMenuItemClick(item)
            }
        }
    }

    private fun handleMenuItemClick(item: MenuItem) {
        when (item.id) {
            1 -> {
                // Launch xCloud
                Log.i(TAG, "Launching xCloud")
                val intent = Intent(requireContext(), XCloudWebViewActivity::class.java)
                startActivity(intent)
            }
            2 -> {
                // Open Settings
                Log.i(TAG, "Opening Settings")
                val intent = Intent(requireContext(), SettingsActivity::class.java)
                startActivity(intent)
            }
            3 -> {
                // Show About
                Log.i(TAG, "Showing About")
                showAboutDialog()
            }
        }
    }

    private fun showAboutDialog() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setTitle("About Better xCloud")
        builder.setMessage(
            "Better xCloud for Android TV\n\n" +
            "Version: ${com.betterxcloud.android.BuildConfig.VERSION_NAME}\n\n" +
            "An enhanced Xbox Cloud Gaming experience for Android TV and Fire Stick.\n\n" +
            "Visit: https://better-xcloud.github.io"
        )
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}

/**
 * Data class for menu items
 */
data class MenuItem(
    val id: Int,
    val title: String,
    val description: String,
    val iconResId: Int
)

/**
 * Card presenter for menu items
 */
class CardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = androidx.leanback.widget.ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            setCardType(androidx.leanback.widget.BaseCardView.CARD_TYPE_INFO_UNDER)
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val menuItem = item as MenuItem
        val cardView = viewHolder.view as androidx.leanback.widget.ImageCardView

        cardView.titleText = menuItem.title
        cardView.contentText = menuItem.description

        // Set icon
        cardView.setMainImageDimensions(313, 176)
        cardView.mainImageView.setImageResource(menuItem.iconResId)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as androidx.leanback.widget.ImageCardView
        cardView.mainImage = null
    }
}
