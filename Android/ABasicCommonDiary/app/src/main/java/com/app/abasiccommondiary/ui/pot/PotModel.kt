package com.app.abasiccommondiary.ui.pot

class PotModel(pot_name: String?, pot_type: String?, pot_amountprev: String?, pot_amount: String?) {
    private var pot_name: String
    private var pot_type: String
    private var pot_amountprev: String
    private var pot_amount: String
    init {
        this.pot_name = pot_name!!
        this.pot_type = pot_type!!
        this.pot_amountprev = pot_amountprev!!
        this.pot_amount = pot_amount!!
    }
    fun getPotname(): String? {
        return pot_name
    }
    fun setPotname(pot_name: String?) {
        this.pot_name = pot_name!!
    }
    fun getPottype(): String? {
        return pot_type
    }
    fun setPottype(pot_type: String?) {
        this.pot_type = pot_type!!
    }
    fun getPotamountprev(): String? {
        return pot_amountprev
    }
    fun setPotamountprev(pot_amountprev: String?) {
        this.pot_amountprev = pot_amountprev!!
    }
    fun getPotamount(): String? {
        return pot_amount
    }
    fun setPotamount(pot_amount: String?) {
        this.pot_amount = pot_amount!!
    }
}