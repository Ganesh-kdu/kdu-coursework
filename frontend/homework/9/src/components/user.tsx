import "./user.scss"
interface userProps{name:string,fullName:string,designation:string}
export const User = ({name,fullName,designation}:userProps) => {
    return(
        <div className="user-details">
            <div className="detail">{name}</div>
            <div className="sub-detail">{fullName}</div>
            <div className="detail">{designation}</div>
        </div>
    );
};