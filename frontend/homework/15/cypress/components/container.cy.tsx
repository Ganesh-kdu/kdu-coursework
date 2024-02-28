import { Provider } from "react-redux";
import { store, StoreType } from "../../src/redux/store";
import MainContainer from "../../src/components/container";
import { check } from "../../src/redux/listSlice";
declare global {
    interface Window {
        store:StoreType;
    }
}


describe("<Header />", () => {
    beforeEach(() => {
        cy.mount(
            <Provider store={store}>
                <MainContainer />
            </Provider>
        )
        window.store = store;
    });
    it("button disabled at start", () => {
        cy.get("input#newItem").should("exist");
        cy.get("input#newItem").should("have.value", "");
        cy.get("button#submit").should("exist");
        cy.get("button#submit").should("be.disabled");
    });
    it("button enabled on typing", () => {
        cy.get("input#newItem").type("test");
        cy.get("button#submit").should("be.enabled");
    });
    it("button disabled again on removing text", () => {
        cy.get("input#newItem").type("test");
        cy.get("button#submit").should("be.enabled");
        cy.get("input#newItem").clear();
        cy.get("button#submit").should("be.disabled");
    });
    it("check if new item added to store", () => {
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
    it("checked item (according to store) removed from store", () => {
        const checked = "checked"
        cy.get("input#newItem").type(checked);
        cy.get("button#submit").click();

        const unchecked = "unchecked"
        cy.get("input#newItem").type(unchecked);
        cy.get("button#submit").click();

        cy.window()
            .its("store")
            .then((store:StoreType) => {
                const state = store.getState();
                store.dispatch(check(state.list.list.indexOf(checked)))
            })
            

        cy.get("button#clear").click();

        cy.window()
            .its("store")
            .invoke("getState")
            .then((state: any) => {
                const list = state.list.list;
                expect(list).to.deep.not.include("checked");
            });
    });
});
