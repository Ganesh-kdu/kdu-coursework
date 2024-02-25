import "../styles/home.scss"
import ProductTile from "./productTile";
function Home() {
    return(
        <div>
            <div className="title">
                <h1 className="kdu">KDU</h1><h1 className="marketplace">MARKETPLACE</h1>
                {
                    <ProductTile/>
                }
            </div>
        </div>
    )
}
export default Home;