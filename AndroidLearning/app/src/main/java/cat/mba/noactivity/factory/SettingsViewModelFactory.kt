package cat.mba.noactivity.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cat.mba.noactivity.features.settings.component.viewModel.SettingsViewModel
import cat.mba.noactivity.repository.ConfiguracioRepository

class SettingsViewModelFactory(
    private val repo: ConfiguracioRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}