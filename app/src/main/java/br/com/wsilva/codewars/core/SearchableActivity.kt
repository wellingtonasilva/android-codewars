package br.com.wsilva.codewars.core

import android.os.Bundle
import br.com.wsilva.codewars.R

class SearchableActivity: BasicActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_searchable_activity)
    }
}