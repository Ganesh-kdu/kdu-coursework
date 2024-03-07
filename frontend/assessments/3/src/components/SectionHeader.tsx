import { createUseStyles } from "react-jss";

function SectionHeader ({content}:Readonly<{content:string}>){
    const useStyles = createUseStyles({
        sectionHeader: {
            left: '10px',
            right: '10px',
            fontSize: '13px',
            fontWeight: '600',
            backgroundColor: "#F08A5D",
            color: 'white',
            padding: '15px',
            marginBottom: '10px'
        }
      })
      const classes = useStyles()
    return(
        <div className={classes.sectionHeader}>
            {content}
        </div>
    )
}
export default SectionHeader;