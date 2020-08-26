// import React, { Component } from 'react';
// import { Link, withRouter, RouteComponentProps } from 'react-router-dom';
// import { Dropdown, Collapse } from 'react-bootstrap';
// import { connect } from 'react-redux';
// import { EntitiesMenu, SignInMenu } from '../menus/entities';
// import AdminMenu from '../menus/admin';
// import { AccountMenu } from '../menus/account';

// export interface ISidebarProps {
//     isAuthenticated: boolean;
//     isAdmin: boolean;
//     isSwaggerEnabled: boolean;
// }


// const Sidebar = (props: ISidebarProps) => {

//     return (
//         <nav className="sidebar sidebar-offcanvas" id="sidebar">
//             <div className="text-center sidebar-brand-wrapper d-flex align-items-center">
//                 <a className="sidebar-brand brand-logo" href="/"><img src={"content/star_admin/images/logo.svg"} alt="logo" /></a>
//                 <a className="sidebar-brand brand-logo-mini pt-3" href="/"><img src={"content/star_admin/images/logo-mini.svg"} alt="logo" /></a>
//             </div>
//             <ul className="nav" data-widget="treeview" role="menu" data-accordion="false">
//                 {/* {props.isAuthenticated && !props.isAdmin && <EntitiesMenu />} */}
//                 {props.isAuthenticated && props.isAdmin && <AdminMenu showSwagger={props.isSwaggerEnabled} />}
//                 {!props.isAuthenticated && <SignInMenu />}
//                 {props.isAuthenticated && <AccountMenu />}
//             </ul>
//         </nav>
//     );
// }

// export default Sidebar;

import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Collapse } from 'react-bootstrap';
import { Dropdown } from 'react-bootstrap';

interface SideBarProps {
  isAuthenticated: boolean;
  isAdmin: boolean;
  isSwaggerEnabled: boolean;
}

// interface SideBarState {
//   basicUiMenuOpen: boolean;
//   userPagesMenuOpen: boolean;
// }

class Sidebar extends Component<SideBarProps> {
  state = {
    basicUiMenuOpen: false,
    userPagesMenuOpen: false,
    accountOpen: false
  }

  toggleMenuState = (menuState) => {
    if (this.state[menuState]) {
      this.setState({ [menuState]: false });
    } else if (Object.keys(this.state).length === 0) {
      this.setState({ [menuState]: true });
    } else {
      Object.keys(this.state).forEach(i => {
        this.setState({ [i]: false });
      });
      this.setState({ [menuState]: true });
    }
  }

  // componentDidUpdate(prevProps) {
  //   if (window.location !== prevProps.location) {
  //     this.onRouteChanged();
  //   }
  // }

  onRouteChanged() {
    document.querySelector('#sidebar').classList.remove('active');
    Object.keys(this.state).forEach(i => {
      this.setState({ [i]: false });
    });

    const dropdownPaths = [
      { path: '/', state: 'basicUiMenuOpen' },
      { path: '/form-elements', state: 'formElementsMenuOpen' },
      { path: '/tables', state: 'tablesMenuOpen' },
      { path: '/icons', state: 'iconsMenuOpen' },
      { path: '/charts', state: 'chartsMenuOpen' },
      { path: '/user-pages', state: 'userPagesMenuOpen' },
    ];

    dropdownPaths.forEach((obj => {
      if (this.isPathActive(obj.path)) {
        this.setState({ [obj.state]: true })
      }
    }));

  }
  render() {
    return (
      <nav className="sidebar sidebar-offcanvas" id="sidebar">
        <div className="text-center sidebar-brand-wrapper d-flex align-items-center">
          <a className="sidebar-brand brand-logo" href="index.html"><img src={"content/star_admin/images/logo.svg"} alt="logo" /></a>
          <a className="sidebar-brand brand-logo-mini pt-3" href="index.html"><img src={"content/star_admin/images/logo-mini.svg"} alt="logo" /></a>
        </div>
        <ul className="nav">
          <li className={this.isPathActive('/dashboard') ? 'nav-item active' : 'nav-item'}>
            <Link className="nav-link" to="/dashboard">
              <i className="mdi mdi-television menu-icon"></i>
              <span className="menu-title">Dashboard</span>
            </Link>
          </li>
          <li className={this.isPathActive('/') ? 'nav-item active' : 'nav-item'}>
            <div className={this.state.basicUiMenuOpen ? 'nav-link menu-expanded' : 'nav-link'} onClick={() => this.toggleMenuState('basicUiMenuOpen')} data-toggle="collapse">
              <i className="mdi mdi-crosshairs-gps menu-icon"></i>
              <span className="menu-title">Entities</span>
              <i className="right fa fa-angle-right menu-arrow"></i>
            </div>
            <Collapse in={this.state.basicUiMenuOpen}>
              <ul className="nav flex-column sub-menu">
                <li className="nav-item"> <Link className={this.isPathActive('/batiment') ? 'nav-link active' : 'nav-link'} to="/batiment">Batiment</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/client') ? 'nav-link active' : 'nav-link'} to="/client">Client</Link></li>
                {/* <li className="nav-item"> <Link className={this.isPathActive('/depense') ? 'nav-link active' : 'nav-link'} to="/depense">Depense</Link></li> */}
                <li className="nav-item"> <Link className={this.isPathActive('/emplacement') ? 'nav-link active' : 'nav-link'} to="/emplacement">Emplacement</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/employe') ? 'nav-link active' : 'nav-link'} to="/employe">Employe</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/facture') ? 'nav-link active' : 'nav-link'} to="/facture">Facture</Link></li>
                {/* <li className="nav-item"> <Link className={this.isPathActive('/fonction') ? 'nav-link active' : 'nav-link'} to="/fonction">Fonction</Link></li> */}
                <li className="nav-item"> <Link className={this.isPathActive('/fournisseur') ? 'nav-link active' : 'nav-link'} to="/fournisseur">Fournisseur</Link></li>
                {/* <li className="nav-item"> <Link className={this.isPathActive('/ligne-eclairage') ? 'nav-link active' : 'nav-link'} to="/ligne-eclairage">Ligne-Eclairage</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/log-parametre-environement') ? 'nav-link active' : 'nav-link'} to="/log-parametre-environement">Log-Parametre-Environment</Link></li> */}
                <li className="nav-item"> <Link className={this.isPathActive('/produit') ? 'nav-link active' : 'nav-link'} to="/produit">Produit</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/phase-production') ? 'nav-link active' : 'nav-link'} to="/phase-production">Phase Production</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/stock') ? 'nav-link active' : 'nav-link'} to="/stock">Stock</Link></li>
                {/* <li className="nav-item"> <Link className={this.isPathActive('/type-produit') ? 'nav-link active' : 'nav-link'} to="/type-produit">Type-Produit</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/vente') ? 'nav-link active' : 'nav-link'} to="/vente">Vente</Link></li> */}
              </ul>
            </Collapse>
          </li>
          {this.props.isAdmin ?
          <li className={this.isPathActive('/admin') ? 'nav-item active' : 'nav-item'}>
            <div className={this.state.userPagesMenuOpen ? 'nav-link menu-expanded' : 'nav-link'} onClick={() => this.toggleMenuState('userPagesMenuOpen')} data-toggle="collapse">
              <i className="mdi mdi-lock-outline menu-icon"></i>
              <span className="menu-title">Administration</span>
              <i className="right fa fa-angle-right menu-arrow"></i>
            </div>
            <Collapse in={this.state.userPagesMenuOpen}>
              <ul className="nav flex-column sub-menu">
                <li className="nav-item"> <Link className={this.isPathActive('/admin/user-management') ? 'nav-link active' : 'nav-link'} to="/admin/user-management">User Management</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/admin/metrics') ? 'nav-link active' : 'nav-link'} to="/admin/metrics">Metrics</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/admin/health') ? 'nav-link active' : 'nav-link'} to="/admin/health">Health</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/admin/configuration') ? 'nav-link active' : 'nav-link'} to="/admin/configuration">Configuration</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/admin/audits') ? 'nav-link active' : 'nav-link'} to="/admin/audits">Audits</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/admin/logs') ? 'nav-link active' : 'nav-link'} to="/admin/logs">Logs</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/admin/api') ? 'nav-link active' : 'nav-link'} to="/admin/api">Api</Link></li>
              </ul>
            </Collapse>
          </li> : null}
          <li className={this.isPathActive('/account') ? 'nav-item active' : 'nav-item'}>
            <div className={this.state.userPagesMenuOpen ? 'nav-link menu-expanded' : 'nav-link'} onClick={() => this.toggleMenuState('accountOpen')} data-toggle="collapse">
              <i className="mdi mdi-lock-outline menu-icon"></i>
              <span className="menu-title">Account</span>
              <i className="right fa fa-angle-right menu-arrow"></i>
            </div>
            <Collapse in={this.state.accountOpen}>
              <ul className="nav flex-column sub-menu">
                <li className="nav-item"> <Link className={this.isPathActive('/account/settings') ? 'nav-link active' : 'nav-link'} to="/account/settings">User Settings</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/account/password') ? 'nav-link active' : 'nav-link'} to="/account/password">Password</Link></li>
                <li className="nav-item"> <Link className={this.isPathActive('/logout') ? 'nav-link active' : 'nav-link'} to="/logout">Logout</Link></li>
              </ul>
            </Collapse>
          </li>
        </ul>
      </nav>
    );
  }

  isPathActive(path) {
    return window.location.pathname.startsWith(path);
  }

  componentDidMount() {
    this.onRouteChanged();
    // add className 'hover-open' to sidebar navitem while hover in sidebar-icon-only menu
    const body = document.querySelector('body');
    document.querySelectorAll('.sidebar .nav-item').forEach((el) => {

      el.addEventListener('mouseover', function () {
        if (body.classList.contains('sidebar-icon-only')) {
          el.classList.add('hover-open');
        }
      });
      el.addEventListener('mouseout', function () {
        if (body.classList.contains('sidebar-icon-only')) {
          el.classList.remove('hover-open');
        }
      });
    });
  }

}

export default Sidebar;
