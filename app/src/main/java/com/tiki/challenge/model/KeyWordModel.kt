package com.tiki.challenge.model

import com.tiki.challenge.utils.DrawableUtils

class KeyWordModel(var data: String) : BaseModel() {
    var color: Int = DrawableUtils.generateColor()
}