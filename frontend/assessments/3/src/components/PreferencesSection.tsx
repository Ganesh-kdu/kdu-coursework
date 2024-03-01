import { createUseStyles } from "react-jss";
import SectionHeader from "./SectionHeader";
import { RootState } from "../redux/store";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { IAddOn, togglePreference } from "../redux/hotelSlice";

function PreferencesSection() {
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
        },
        sectionContainer: {
            marginBottom: "20px",
        },
    });
    const classes = useStyles();
    const { rooms, selected } = useSelector((state: RootState) => state.list);
    const [addOns, setAddOns] = useState<IAddOn[]>();
    const reduxDispatch = useDispatch();

    useEffect(() => {
        const data = rooms.filter((room) => room.id.toString() === selected)[0];
        setAddOns(data?.addOns);
    }, [selected]);
    function toggleAddOn(addOn: IAddOn) {
        document.getElementById(addOn.name)?.classList.toggle(classes.selected);
        reduxDispatch(togglePreference(addOn));
        console.log(addOn.name);
    }
    return (
        <div className={classes.sectionContainer}>
            <SectionHeader
                content={"Select additional add ons / preferences"}
            />
            {selected ? (
                addOns?.map((addOn) => {
                    return (
                        <button
                            className={classes.option}
                            key={addOn.name}
                            id={addOn.name}
                            onClick={() => toggleAddOn(addOn)}
                        >
                            {addOn.name}
                        </button>
                    );
                })
            ) : (
                <div>No room selected</div>
            )}
        </div>
    );
}
export default PreferencesSection;
