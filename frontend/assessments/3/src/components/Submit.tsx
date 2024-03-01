import { createUseStyles } from "react-jss";
import { RootState } from "../redux/store";
import { useSelector } from "react-redux";

function Submit(){
    const useStyles = createUseStyles({
        submit: {
            backgroundColor: "#F08A5D",
            border: "none",
            color: "white",
            padding: '15px',
            fontSize: '15px'
        },
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
        },
        sectionContainer: {
            marginBottom: "20px",
        },
    });
    const classes = useStyles();
    const { bill, duration, preferences } = useSelector((state: RootState) => state.list);
    function generateBill(){
        let preferenceCost = 0;
        preferences.map((preference)=>preferenceCost+=parseInt(preference.cost))
        console.log("Cost per day [base]: ", bill-preferenceCost);
        console.log("Cost per day [preferences]: ", preferenceCost);
        console.log("Cost per day [total]: ", bill);
        console.log("Cost [total]: ", bill*duration);

        console.log("FInal Bill [w GST] = ", bill*duration*1.18)
        
    }
    return(
        <div>
            <div>{bill*duration} + 18% GST = {bill*duration*1.18}</div>
            <button className={classes.submit} onClick={generateBill}>Submit</button>
        </div>
    )
}
export default Submit;