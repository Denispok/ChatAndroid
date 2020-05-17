package com.example.sampleapplication.data

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = EmptyDeserializer::class)
class Empty {
    companion object {
        val instance = Empty()
    }
}

class EmptyDeserializer : JsonDeserializer<Empty>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Empty {
        return Empty.instance
    }
}
