import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { NavLink as Link } from 'react-router-dom';

import { NavDropdown } from './menu-components';
import { Translate } from 'react-jhipster';

export const AccountMenu = props => (
  <>
    <li className="nav-item">
      <Link to="#" className="nav-link">
      <i className="mdi mdi-account-outline menu-icon"></i>
        <span className="menu-title">
          Account
          <i className="menu-arrow"></i>
        </span>
      </Link>
      <ul className="nav">
        <li className="nav-item">
          <Link to="/account/settings" className="nav-link">
          <i className="mdi mdi-lock-outline menu-icon"></i>
            <span className="menu-title">
              Setting
            </span>
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/account/password" className="nav-link">
          <i className="mdi mdi-lock-outline menu-icon"></i>
            <span className="menu-title">
              Password
            </span>
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/logout" className="nav-link">
          <i className="mdi mdi-logout menu-icon"></i>
            <span className="menu-title">
              Sign out
            </span>
          </Link>
        </li>
      </ul>
    </li>
  </>
);
