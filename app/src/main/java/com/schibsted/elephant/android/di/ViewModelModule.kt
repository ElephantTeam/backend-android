package com.schibsted.elephant.android.di

import com.schibsted.elephant.android.ui.leaderboard.model.LeaderboardViewModel
import com.schibsted.elephant.android.ui.challenge.ChallengeViewModel
import com.schibsted.elephant.android.ui.entry.EntryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { EntryViewModel(get(), get()) }
    viewModel { ChallengeViewModel(get(), get()) }
    viewModel { LeaderboardViewModel(get()) }
}
