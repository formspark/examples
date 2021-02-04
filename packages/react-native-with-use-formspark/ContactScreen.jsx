import React, { useState } from "react";
import { Alert, Button, Text, TextInput, View } from "react-native";
import { useFormspark } from "@formspark/use-formspark";

const FORMSPARK_FORM_ID = "your-form-id";

function ContactScreen() {
  const [submit, submitting] = useFormspark({
    formId: FORMSPARK_FORM_ID,
  });

  const [message, setMessage] = useState("");

  const onPress = async () => {
    await submit({ message });
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
        <Button title="Send" onPress={onPress} disabled={submitting} />
      </View>
    </View>
  );
}

export default ContactScreen;
