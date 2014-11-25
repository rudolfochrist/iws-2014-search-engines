package de.hsmannheim.iws2014.indexing;

import net.sf.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class DeveloperTest {

    @Test
    public void it_can_serialize_itself_to_json() throws  Exception {
        Developer bengara = new Developer().addFirstname("Bengara");
        JSONObject json = bengara.toJSON();
        assertThat(json.getString("firstname"), equalTo("Bengara"));
    }
    @Test
    public void a_new_instance_can_be_initialized_with_json() throws Exception {
//        JsonObject json = new JsonObject();
//        json.add("residence", "Duckburg");
//        Developer developer = new Developer(json);
//
//        assertThat(developer.residence, is(equalTo("Duckburg")));
    }
}
