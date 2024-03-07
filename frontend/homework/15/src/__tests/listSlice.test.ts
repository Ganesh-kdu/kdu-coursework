import listReducer, {
    addToList,
    clear,
    setList,
    check,
} from "../redux/listSlice";
import { expect, describe, it } from "vitest";

describe("listSlice reducer", () => {
    it("should handle adding an item to the list", () => {
        const initialState = { list: [], checked: [] };
        const newState = listReducer(initialState, addToList("New Item"));
        expect(newState.list).toEqual(["New Item"]);
    });

    it("should handle clearing the checked items", () => {
        const initialState = { list: [], checked: [0, 1, 2] };
        const newState = listReducer(initialState, clear());
        expect(newState.checked).toEqual([]);
    });

    it("should handle setting the list", () => {
        const initialState = { list: [], checked: [] };
        const newState = listReducer(
            initialState,
            setList(["Item 1", "Item 2"])
        );
        expect(newState.list).toEqual(["Item 1", "Item 2"]);
    });

    it("should handle checking an item", () => {
        const initialState = { list: ["Item 1", "Item 2"], checked: [] };
        const newState = listReducer(initialState, check(0));
        expect(newState.checked).toEqual([0]);
    });

    it("should handle unchecking an item", () => {
        const initialState = { list: ["Item 1", "Item 2"], checked: [0, 1] };
        const newState = listReducer(initialState, check(0));
        expect(newState.checked).toEqual([1]);
    });

    it("should handle checking an already checked item", () => {
        const initialState = { list: ["Item 1", "Item 2"], checked: [0] };
        const newState = listReducer(initialState, check(0));
        expect(newState.checked).toEqual([]);
    });
});
