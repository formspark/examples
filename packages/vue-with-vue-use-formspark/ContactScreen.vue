<template>
  <form @submit="onSubmit">
    <textarea v-model="message" @input="onInput" />
    <button type="submit" :disabled="submitting">Send</button>
  </form>
</template>

<script>
import { ref } from "vue";
import { useFormspark } from "@formspark/vue-use-formspark";
export default {
  setup() {
    const message = ref("");

    const [submit, submitting] = useFormspark({
      formId: "your-form-id",
    });

    const onInput = (e) => {
      message.value = e.target.value;
    };

    const onSubmit = async (e) => {
      e.preventDefault();
      await submit({ message: message.value });
      message.value = "";
    };

    return {
      message,
      onInput,
      onSubmit,
      submitting,
    };
  },
};
</script>
