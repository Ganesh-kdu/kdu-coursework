import { Oval } from "react-loader-spinner";

const Loader = () => {
    return (
        <div
            style={{
                display: "flex",
                height: "100vh",
                alignItems: "center",
                justifyContent: "center",
            }}
        >
            <Oval
                visible={true}
                height="80"
                width="80"
                color="darkblue"
                ariaLabel="oval-loading"
                wrapperStyle={{}}
                wrapperClass=""
            />{" "}
        </div>
    );
};

export default Loader;