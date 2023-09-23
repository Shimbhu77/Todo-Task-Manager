import "./App.css";
import Navbar from "./Components/Navbar";
import TaskForm from "./Components/TaskForm";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; // Import the Route and Routes components
import Home from "./Components/Home";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/taskform" element={<TaskForm />} />
          <Route path="/sign-in" element={<Home />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
