import Header from "../../src/components/header";
import { Provider } from "react-redux";
import { store } from "../../src/redux/store";
describe("<Header />", () => {
    beforeEach(() => {
        cy.mount(
            <Provider store={store}>
                <Header />
            </Provider>
        );
    });
    it("Shoud be able to type in search field", () => {
        cy.get("input#search").should("exist");
        cy.get("input#search").should("have.value", "");
        cy.get("input#search").type("test search query");
        cy.get("input#search").should("have.value", "test search query");
    });
});
