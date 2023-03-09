import "./View3.css";
import { useState } from 'react';
import Calendar from 'react-calendar';
import "react-calendar/dist/Calendar.css";

function Calander() {

  const [value, onChange] = useState(new Date());

  return (
    <>
      <div>
        <Calendar className="calendar" locale="en-EN" onChange={onChange} value={value} />
        {console.log(value)}
      </div>
    </>
  );
}
export default Calander