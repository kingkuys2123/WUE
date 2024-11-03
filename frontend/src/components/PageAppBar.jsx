import React, {useEffect, useState} from "react";
import "./styles/FontStyle.css";
import { AppBar, Box, Button, Toolbar, Typography, Menu, MenuItem } from "@mui/material";
import {useNavigate} from "react-router-dom";

function PageAppBar({ title }) {
    const nav = useNavigate();

    const [currentUser, setCurrentUser] = useState({});

    const [anchorEl, setAnchorEl] = useState(null);

    useEffect(() => {
        const loggedInUser = localStorage.getItem('user');
        if(loggedInUser) {
            setCurrentUser(JSON.parse(loggedInUser));
        }
    }, []);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <div>
            <AppBar sx={{ backgroundColor: '#FFFFFF', color: 'black', boxShadow: 'none' }} position="static">
                <Box>
                    <Toolbar disableGutters sx={{ height: '105px' }}>
                        <Typography className="project-name" variant="h4" component="div" sx={{ flexGrow: 1, padding: "25px" }}>
                            <span>{title}</span>
                        </Typography>
                        <Box sx={{ padding: '25px' }}>
                            <Button sx={{ borderRadius: '50%' }} onClick={handleClick}>
                                <img src="/assets/placeholders/avatar-photo-placeholder.png" alt="profile-picture" style={{ width: '50px', height: '50px', borderRadius: '50%' }}/>
                            </Button>
                            <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleClose}>
                                <MenuItem>
                                    <span>My Account</span>
                                </MenuItem>
                            </Menu>
                        </Box>
                    </Toolbar>
                </Box>
            </AppBar>
        </div>
    );
}

export default PageAppBar;