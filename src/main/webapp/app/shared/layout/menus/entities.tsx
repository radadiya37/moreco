import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';
import { Translate } from 'react-jhipster';

export const EntitiesMenu = props => (
  <>
    <li className="nav-item has-treeview">
      <Link to="#" className="nav-link">
        <i className="nav-icon fa fa-user" />
        <p>
          <Translate contentKey="global.menu.account.entities">Entities</Translate>
          <i className="right fa fa-angle-left" />
        </p>
      </Link>
      <ul className="nav nav-treeview">
        <li className="nav-item">
          <Link to="/entity/ts-transit/edit" className="nav-link">
            <i className="nav-icon fa fa-globe" />
            <p>
              <Translate contentKey="global.menu.entities.tsTransit">Transit Application</Translate>
            </p>
          </Link>
        </li>
        {/* <li className="nav-item">
          <Link to="/entity/ts-transit-activity" className="nav-link">
            <i className="nav-icon fa fa-globe" />
            <p>
              <Translate contentKey="global.menu.entities.tsTransitActivity">Transit Activity</Translate>
            </p>
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/entity/ts-transit-expense" className="nav-link">
            <i className="nav-icon fa fa-globe" />
            <p>
              <Translate contentKey="global.menu.entities.tsTransitExpense">Transit Expense</Translate>
            </p>
          </Link>
        </li> */}
        <li className="nav-item">
          <Link to="/entity/ts-fare-card" className="nav-link">
            <i className="nav-icon fa fa-globe" />
            <p>
              <Translate contentKey="global.menu.entities.tsFareCard">Fare Card</Translate>
            </p>
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/entity/ts-customer-service" className="nav-link">
            <i className="nav-icon fa fa-globe" />
            <p>
              <Translate contentKey="global.menu.entities.tsCustomerService">Customer Service</Translate>
            </p>
          </Link>
        </li>
      </ul>
    </li>
  </>
);

export const SignInMenu = props => (
  <>
    <li className="nav-item has-treeview">
      <Link to="#" className="nav-link">
        <i className="nav-icon fa fa-user" />
        <p>
          <Translate contentKey="global.menu.account.main">Account</Translate>
          <i className="right fa fa-angle-left" />
        </p>
      </Link>
      <ul className="nav nav-treeview">
        <li className="nav-item">
          <Link to="/login" className="nav-link">
            <i className="nav-icon fa fa-sign-in" />
            <p>
              <Translate contentKey="global.menu.account.login">Sign in</Translate>
            </p>
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/account/register" className="nav-link">
            <i className="nav-icon fa fa-sign-in" />
            <p>
              <Translate contentKey="global.menu.account.register">Register</Translate>
            </p>
          </Link>
        </li>
      </ul>
    </li>
  </>
);