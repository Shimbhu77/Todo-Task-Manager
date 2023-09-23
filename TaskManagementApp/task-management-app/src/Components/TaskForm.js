import React, { useState } from "react";

export default function TaskForm() {

  // Define state variables
  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");
  const [dueDate, setDueDate] = useState("");

  // Event handler for handling form submission
  const createTask = () => {
    // Create a task object with the form data
    const task = {
      title,
      desc,
      dueDate,
    };

     // Make a POST request to your API endpoint
    fetch("https://example.com/api/tasks", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(task),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((data) => {
          // Handle the API response here 
          alert("Task Added Successfully.");
          // Clear the form after successful submission
          setTitle("");
          setDesc("");
          setDueDate("");
        })
        .catch((error) => {
          // Handle any errors that occurred during the fetch
          console.error("There was a problem with the fetch operation:", error);
        });
  };

  return (
    <>
      <div className="container">
        <div className="mb-3 mt-3">
          <label htmlFor="exampleFormControlInput1" className="form-label">
            Task Name
          </label>
          <input
            type="text"
            className="form-control"
            id="exampleFormControlInput1"
            placeholder="Enter your Task Title"
            value={title} // Connect value to state
            onChange={(e) => setTitle(e.target.value)} // Update state on input change
          />
        </div>
        <div className="mb-3">
          <label htmlFor="exampleFormControlTextarea1" className="form-label">
            Task Description
          </label>
          <textarea
            className="form-control"
            id="exampleFormControlTextarea1"
            rows="5"
            value={desc} // Connect value to state
            onChange={(e) => setDesc(e.target.value)} // Update state on input change
          ></textarea>
        </div>
        <div className="form-group">
          <label>Task Due Date</label>
          <input
            type="date"
            className="form-control"
            value={dueDate} // Connect value to state
            onChange={(e) => setDueDate(e.target.value)} // Update state on input change
          />
        </div>

        <button
          type="button"
          className="btn btn-primary mt-3"
          onClick={createTask}
        >
          Submit Task
        </button>
      </div>
    </>
  );
}
