import React, { useState } from "react";
import { useFormspark } from "@formspark/use-formspark";

const FORMSPARK_FORM_ID = "your-form-id";

function ContactPage() {
  const [submit, submitting] = useFormspark({
    formId: FORMSPARK_FORM_ID,
  });
  const [message, setMessage] = useState("");
  return (
    <form
      onSubmit={async (e) => {
        e.preventDefault();
        await submit({ message });
        alert("Form submitted");
      }}
    >
      <textarea value={message} onChange={(e) => setMessage(e.target.value)} />
      <button type="submit" disabled={submitting}>
        Send
      </button>
    </form>
  );
}

export default ContactPage;
