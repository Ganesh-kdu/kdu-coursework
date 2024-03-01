import { createUseStyles } from "react-jss";
import SectionHeader from "./SectionHeader";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { IRoom, selectRoom } from "../redux/hotelSlice";

function RoomSection() {
    const useStyles = createUseStyles({
        option: {
            border: "1px solid #B3B3B3",
            fontSize: "15px",
            padding: "10px",
            marginBottom: "10px",
            marginRight: "10px",
            backgroundColor: "white",
        },
        selected: {
            borderColor: "#F3A27F",
            color: "#F3A27F",
        },
        optionsContainer: {
            display: "flex",
            flexWrap: "wrap"
        },
        sectionContainer: {
            marginBottom: "20px",
        },
    });
    const classes = useStyles();
    const reduxDispatch = useDispatch();
    const { rooms, selected } = useSelector((state: RootState) => state.list);
    return (
        <div className={classes.sectionContainer}>
            <SectionHeader content={"Select Room Type"} />
            <div className={classes.optionsContainer}>
                {rooms.map((room: IRoom) => {
                    return (
                        <button
                            className={
                                classes.option +
                                " " +
                                (selected == room.id.toString()
                                    ? classes.selected
                                    : "")
                            }
                            key={room.id}
                            onClick={() =>
                                reduxDispatch(selectRoom(room))
                            }
                        >
                            {room.name}
                        </button>
                    );
                })}
            </div>
        </div>
    );
}
export default RoomSection;
