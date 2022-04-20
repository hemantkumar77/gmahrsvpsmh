package com.app.abasiccommondiary.ui.train

class TrainModel(train_time: String?, train_no: String?, train_name: String?, train_source: String?, train_via: String?, train_destination: String?) {
    private var train_time: String
    private var train_no: String
    private var train_name: String
    private var train_source: String
    private var train_via: String
    private var train_destination: String
    init {
        this.train_time = train_time!!
        this.train_no = train_no!!
        this.train_name = train_name!!
        this.train_source = train_source!!
        this.train_via = train_via!!
        this.train_destination = train_destination!!
    }
    fun getTraintime(): String? {
        return train_time
    }
    fun setTraintime(train_time: String?) {
        this.train_time = train_time!!
    }
    fun getTrainno(): String? {
        return train_no
    }
    fun setTrainno(train_no: String?) {
        this.train_no = train_no!!
    }
    fun getTrainname(): String? {
        return train_name
    }
    fun setTrainname(train_name: String?) {
        this.train_name = train_name!!
    }
    fun getTrainsource(): String? {
        return train_source
    }
    fun setTrainsource(train_source: String?) {
        this.train_source = train_source!!
    }
    fun getTrainvia(): String? {
        return train_via
    }
    fun setTrainvia(train_via: String?) {
        this.train_via = train_via!!
    }
    fun getTraindestination(): String? {
        return train_destination
    }
    fun setTraindestination(train_destination: String?) {
        this.train_destination = train_destination!!
    }
}