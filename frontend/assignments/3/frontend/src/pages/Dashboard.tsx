import * as React from "react";
import SwipeableViews from "react-swipeable-views";
import { useTheme } from "@mui/material/styles";
import AppBar from "@mui/material/AppBar";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Explore from "../components.tsx/explore";
import Watchlist from "../components.tsx/watchlist";

interface TabPanelProps {
    children?: React.ReactNode;
    dir?: string;
    index: number;
    value: number;
}

function TabPanel(props: Readonly<TabPanelProps>) {
    const { children, value, index, ...other } = props;

    return (
        <Typography
            component="div"
            role="tabpanel"
            hidden={value !== index}
            id={`action-tabpanel-${index}`}
            aria-labelledby={`action-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box sx={{ margin: "auto", width: "75%" }}>{children}</Box>
            )}
        </Typography>
    );
}

function a11yProps(index: any) {
    return {
        id: `action-tab-${index}`,
        "aria-controls": `action-tabpanel-${index}`,
    };
}

export default function FloatingActionButtonZoom() {
    const theme = useTheme();
    const [value, setValue] = React.useState(0);

    const handleChange = (_event: unknown, newValue: number) => {
        setValue(newValue);
    };

    const handleChangeIndex = (index: number) => {
        setValue(index);
    };

    return (
        <Box
            sx={{
                bgcolor: "background.none",
                width: "100%",
                position: "relative",
                minHeight: 200,
            }}
        >
            <AppBar
                position="static"
                color="default"
                sx={{ bgcolor: "white", boxShadow: "none" }}
            >
                <Tabs
                    value={value}
                    onChange={handleChange}
                    indicatorColor="primary"
                    textColor="primary"
                    aria-label="action tabs example"
                    sx={{ p: 0, height: "max-content" }}
                >
                    <Tab
                        label="Explore"
                        {...a11yProps(0)}
                        sx={{ p: 0, margin: 0, height: "max-content" }}
                    />
                    <Tab
                        label="My Watchlist"
                        {...a11yProps(1)}
                        sx={{ p: 0, margin: 0, height: "max-content" }}
                    />
                </Tabs>
            </AppBar>
            <SwipeableViews
                axis={theme.direction === "rtl" ? "x-reverse" : "x"}
                index={value}
                onChangeIndex={handleChangeIndex}
            >
                <TabPanel value={value} index={0} dir={theme.direction}>
                    <Explore />
                </TabPanel>
                <TabPanel value={value} index={1} dir={theme.direction}>
                    <Watchlist />
                </TabPanel>
            </SwipeableViews>
        </Box>
    );
}
