import { Form } from "./components/Form";
import { ScrollToTop } from "./components/ScrollToTop";
import { Timer } from "./components/Timer";

function App() {
    return (
        <div>
            <h1>useRef Hook Example</h1>
            <ScrollToTop />
            <hr />
            <h2>Form</h2>
            <Form />
            <hr />
            <h2>Timer</h2>
            <Timer />
        </div>
    );
}

export default App;
