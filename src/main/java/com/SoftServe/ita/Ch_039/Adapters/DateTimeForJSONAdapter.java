package com.SoftServe.ita.Ch_039.Adapters;

import com.google.gson.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Type;
import java.util.Date;

public class DateTimeForJSONAdapter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime>{

    final private DateTimeFormatter DATE_TIME_FORMATTER  = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

    @Override
    public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(DATE_TIME_FORMATTER.print(src));
    }

    @Override
    public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        try{
            return new DateTime(DATE_TIME_FORMATTER.parseDateTime(json.getAsString()));
        }catch (IllegalArgumentException e){
            Date date = context.deserialize(json, Date.class);
            return new DateTime(date);
        }
    }

}
