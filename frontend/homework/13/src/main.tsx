import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./styles/reset.scss";
import "./styles/index.scss";

import { ECommerceProvider } from "./context/ProductContext.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
    <ECommerceProvider>
        <App />
    </ECommerceProvider>
);
