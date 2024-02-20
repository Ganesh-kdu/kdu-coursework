import { User } from "./user";
import "./details.scss"
import { About } from "./about";


interface DataProps {
    data: {
        name: string;
        fullName: string;
        designation: string;
        skills: {
            id: number;
            skill: string;
        }[];
        hobbies: {
            id: number;
            hobby: string;
        }[];
    };
}

export const Details = ({data}:DataProps) => {
    
    return(
        <div className="details">
            <User {...data}/>
            <div className="additional-detials">
                <About items = {data.skills} type = {"skills"} />
                <About items = {data.hobbies} type = {"hobbies"} />
            </div>
        </div>
    )
}