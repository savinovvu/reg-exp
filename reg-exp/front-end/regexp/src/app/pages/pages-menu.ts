import { NbMenuItem } from '@nebular/theme';



export const MENU_ITEMS: NbMenuItem[] = [

  {
    title: 'Welcome',
    icon: 'home-outline',
    url: '/landing',
    target: '_blank',
  },
  {
    title: 'MAIN',
    group: true,
  },
  {
    title: 'Course',
    icon: 'edit-2-outline',
    link: '/course',
  },
  {
    title: 'Propose a Task',
    icon: 'plus-circle-outline',
    link: '/propose-task',
  },

  {
    title: 'Theory',
    icon: 'text-outline',
    link: '/theory'
  },

  {
    title: 'Rating',
    icon: 'people-outline',
    link: '/users',
  },


  {
    title: 'Auth',
    icon: 'lock-outline',
    children: [
      {
        title: 'Login',
        url: '/login',
        target: '_blank'
      },
      {
        title: 'Register',
        url: '/sign-up',
        target: '_blank'
      },
      {
        title: 'Login as Guest',
        link: '/temporary-login',
      }
    ],
  },

  {
    title: 'Settings',
    icon: 'settings-outline',
    link: '/settings',
  },
];

