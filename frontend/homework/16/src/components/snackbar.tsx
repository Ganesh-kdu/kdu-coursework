import { Alert, Snackbar } from "@mui/material";
import { useSelector } from "react-redux";
import { RootState } from "../context/Store";
import { useState } from "react";

export const SnackBar = () => {
    const { loading } = useSelector(
        (state: RootState) => state.products
    );
    const [ isOpen, setIsOpen ] = useState<boolean|undefined>(true);
    const handleClose = () => {
        setIsOpen(false);
    };
    return (
        <div>
            <Snackbar open={isOpen} onClose={handleClose}>
                <Alert
                    severity="success"
                    variant="filled"
                    sx={{ width: "100%" }}
                    onClose={handleClose}
                >
                    {loading}
                </Alert>
            </Snackbar>
        </div>
    );
};