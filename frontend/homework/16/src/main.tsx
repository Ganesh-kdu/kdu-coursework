import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./styles/reset.scss";
import "./styles/index.scss";
import { store } from "./context/Store.tsx";
import { Provider } from "react-redux";


ReactDOM.createRoot(document.getElementById("root")!).render(
    <Provider store={store}>
        <App />
    </Provider>
);
