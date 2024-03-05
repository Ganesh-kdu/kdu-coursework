import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import { Provider } from "react-redux";
import { store } from "./redux/store.tsx";
import { io } from "socket.io-client";

const socket = io("http://127.0.0.1:3000");
ReactDOM.createRoot(document.getElementById("root")!).render(
    <Provider store={store}>
        <App socket={(socket)}/>
    </Provider>
);
