import './App.css';
import { Route, Routes } from "react-router-dom";
import LandingPage from "./components/LandingPage.jsx";
import UserHome from "./components/user_pages/UserHome.jsx";
import UserBookings from "./components/user_pages/UserBookings.jsx";
import NotFoundPage from "./components/NotFoundPage.jsx";
import MyAccount from "./components/MyAccount.jsx";
import MyEvents from './components/organizer_pages/MyEvents.jsx';
import ViewEventPage from './components/organizer_pages/ViewEventPage.jsx';
import { AuthProvider } from './utils/AuthContext.jsx';
import AdminVenue from './components/admin_pages/AdminVenue.jsx';

function App() {
    return (
        <AuthProvider>
            <Routes>
                <Route path="*" element={<NotFoundPage />} />
                <Route path="/" element={<LandingPage />} />

                <Route path="/home" element={<UserHome />} />
                <Route path="/my_account" element={<MyAccount />} />

                {/* User Routes */}
                <Route path="/user/bookings" element={<UserBookings />} />

                {/* Organizer Routes */}
                <Route path="/myevents" element={<MyEvents />} />
                <Route path="/myevents/:eventId" element={<ViewEventPage />} />

                {/* Admin Routes */}
                <Route path="/admin/venue" element={<AdminVenue />} />

            </Routes>
        </AuthProvider>
    );
}

export default App;
