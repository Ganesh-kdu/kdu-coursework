import { expect, describe, it } from "vitest";
import searchReducer, { setQuery } from "../redux/searchSlice";

describe("searchSlice reducer", () => {
    it("should handle setting the search query", () => {
        const initialState = { searchQuery: "" };
        const newState = searchReducer(initialState, setQuery("New Query"));
        expect(newState.searchQuery).toEqual("New Query");
    });

    it("should handle clearing the search query", () => {
        const initialState = { searchQuery: "Previous Query" };
        const newState = searchReducer(initialState, setQuery(""));
        expect(newState.searchQuery).toEqual("");
    });
    it("should handle setting the same search query", () => {
        const initialState = { searchQuery: "Initial Query" };
        const newState = searchReducer(initialState, setQuery("Initial Query"));
        expect(newState).toEqual(initialState);
    });
});
