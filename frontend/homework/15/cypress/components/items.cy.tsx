import { Provider } from "react-redux";
import { store, StoreType } from "../../src/redux/store";
import { addToList, setList } from "../../src/redux/listSlice";
import Items from "../../src/components/items";
declare global {
    interface Window {
        store: StoreType;
    }
}

const defaultList = ["Item1", "Item2"];
store.dispatch(setList(defaultList));

describe("<Items />", () => {
    beforeEach(() => {
        cy.mount(
            <Provider store={store}>
                <Items />
            </Provider>
        );
        window.store = store;
    });

    it("Should display contents of state on page load", () => {
        defaultList.forEach((listItem) => {
            cy.contains("#list", listItem).should("exist");
        });
    });

    it("Should display updated list on store change", () => {
        store.dispatch(addToList("new item"));
        cy.contains("#list", "new item").should("exist");
    });

    it("List item should get removed on clicking X", () => {
        cy.contains("#list", "Item1").should("exist");
        cy.contains("button", "X").click();
        cy.contains("#list", "Item1").should("not.exist");
    });
});
