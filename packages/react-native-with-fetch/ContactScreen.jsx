import React, { useState } from "react";
import { Alert, Button, Text, TextInput, View } from "react-native";

const FORMSPARK_ACTION_URL = "https://submit-form.com/your-form-id";

function ContactScreen() {
  const [message, setMessage] = useState("");

  const onPress = async () => {
    await fetch(FORMSPARK_ACTION_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({
        message,
      }),
    });
    Alert.alert("Form submitted");
  };

  return (
    <View>
      <View>
        <Text>Message</Text>
        <TextInput
          value={message}
          onChangeText={(message) => setMessage(message)}
          multiline={true}
        />
      </View>
      <View>
        <Button title="Send" onPress={onPress} />
      </View>
    </View>
  );
}

export default ContactScreen;
