import { Provider } from "react-redux";
import { store, StoreType } from "../../src/redux/store";
import MainContainer from "../../src/components/container";
import { check } from "../../src/redux/listSlice";
declare global {
    interface Window {
        store: StoreType;
    }
}

describe("<Header />", () => {
    beforeEach(() => {
        cy.mount(
            <Provider store={store}>
                <MainContainer />
            </Provider>
        );
        window.store = store;
    });
    it("Button should be disabled at start", () => {
        cy.get("input#newItem").should("exist");
        cy.get("input#newItem").should("have.value", "");
        cy.get("button#submit").should("exist");
        cy.get("button#submit").should("be.disabled");
    });
    it("Button should get enabled on typing", () => {
        cy.get("input#newItem").type("test");
        cy.get("button#submit").should("be.enabled");
    });
    it("Button should get disabled again on removing text", () => {
        cy.get("input#newItem").type("test");
        cy.get("button#submit").should("be.enabled");
        cy.get("input#newItem").clear();
        cy.get("button#submit").should("be.disabled");
    });
    it("New item should get added to store", () => {
        cy.get("input#newItem").type("toy");
        cy.get("button#submit").click();
        cy.window()
            .its("store")
            .invoke("getState")
            .then((state: any) => {
                const list = state.list.list;
                expect(list).to.deep.include("toy");
            });
    });
    it("Checked item (according to store) should get removed from store", () => {
        const checked = "checked";
        cy.get("input#newItem").type(checked);
        cy.get("button#submit").click();

        const unchecked = "unchecked";
        cy.get("input#newItem").type(unchecked);
        cy.get("button#submit").click();

        cy.window()
            .its("store")
            .then((store: StoreType) => {
                const state = store.getState();
                store.dispatch(check(state.list.list.indexOf(checked)));
            });

        cy.get("button#clear").click();

        cy.window()
            .its("store")
            .invoke("getState")
            .then((state: any) => {
                const list = state.list.list;
                expect(list).to.not.include("checked");
            });
    });
});
