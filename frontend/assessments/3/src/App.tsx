import { useEffect } from "react";
import Form from "./components/Form";
import { useDispatch } from "react-redux";
import { setRooms } from "./redux/hotelSlice";
import Submit from "./components/Submit";

function App() {
    const reduxDispatch = useDispatch();
    useEffect(() => {
        fetch(
            "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json"
        )
            .then((response) => response.json())
            .then((result) => {
                reduxDispatch(setRooms(result.roomTypes));
            });
    }, []);

    return (
        <>
            <h1>Hotel Booking</h1>
            <Form />
            <Submit />
        </>
    );
}

export default App;
