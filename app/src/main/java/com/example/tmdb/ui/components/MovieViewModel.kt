package com.example.tmdb.ui.components

import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {

    // nao, isso tu mantem no repository
    // aqui tu ia fazer algo tipo...
    // digsmo que tu tenha 3 lsitas de movies A, B e C
    // Tu ia passar um enum pora saber qual buscar
    // Ou
    // Tu pode criar so o MovieComponent recebendo uma lista generica, dai a VM que chamar ele diz qual dado vai usar
    // Dai tu cria tipo, ViewModelA ViewModelB e ViewModelC
    // Cada uma ia ter seu get seus uiStates
    // E dai tu ia passar pro compoentenuma lista generica
}