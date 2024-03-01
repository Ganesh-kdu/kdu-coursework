import { createUseStyles } from "react-jss";
import SectionHeader from "./SectionHeader";
import { useDispatch } from "react-redux";
import { setDuration } from "../redux/hotelSlice";

function DateSection() {
    const useStyles = createUseStyles({
        date: {
            border: "1px solid #B3B3B3",
            fontSize: "13px",
            padding: "10px",
            marginBottom: "10px",
            marginRight: "10px",
        },
        optionsContainer: {
            display: "flex",
        },
        sectionContainer: {
            marginBottom: "20px",
        },
    });
    const classes = useStyles();
    const reduxDispatch = useDispatch();

    function setDates(){
        const startDate = new Date((document.getElementById("start") as HTMLInputElement)!.value)
        const endDate =  new Date((document.getElementById("end") as HTMLInputElement)!.value)
        const diff = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)+1
        if (!Number.isNaN(diff) && diff>0)
            reduxDispatch(setDuration(diff))
    }
    return (
        <div className={classes.sectionContainer}>
            <SectionHeader content={"Select Date"} />
            <div>
                <input type="date" className={classes.date} onChange={setDates} id="start"/>
                <input type="date" className={classes.date} onChange={setDates} id="end"/>
            </div>
        </div>
    );
}
export default DateSection;
