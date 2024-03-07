import "./about.scss";
interface AboutProps {
  items: {
    skill?: string;
    hobby?: string;
    id: number;
  }[];
  type: string;
}
export const About = ({ items, type }: AboutProps) => {
  return (
    <div className={"container " + type}>
      <div className="title">
        {type.charAt(0).toUpperCase() + type.substring(1)}
      </div>
      {items.map((element: any) => (
        <>
          {element.skill ? <div className="item">{element.skill}</div> : ""}
          {element.hobby ? <div className="item">{element.hobby}</div> : ""}
        </>
      ))}
    </div>
  );
};
