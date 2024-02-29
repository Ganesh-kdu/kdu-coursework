import { defineConfig } from "vitest/config";

export default defineConfig({
    test: {
        exclude: ["**/node_modules/**"],
        environment: "happy-dom",
    },
    globals: true

});