import { Nav, Navbar, Container } from "react-bootstrap";

const Mynavbar = () => {
  return (
    <>
      <Navbar bg="black" variant="dark" fixed="top" sticky="top">
        <Container>
          <Navbar.Brand href="/">
            <img
              alt=""
              src={process.env.PUBLIC_URL + "/img/logo_black.png"}
              width="150" height="80" className="logo"
            />{' '}
            <div className="navline">
            사업용차량어쩌고
            </div>
          </Navbar.Brand>
        </Container>
      </Navbar>
    </>
  );
};

export default Mynavbar;


