import { NbMenuItem } from '@nebular/theme';



export const MENU_ITEMS: NbMenuItem[] = [

  {
    title: 'Welcome',
    icon: 'home-outline',
    url: '/',
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
    icon: 'shuffle-2-outline',
    link: '/propose-task',
  },

  {
    title: 'Theory',
    icon: 'text-outline',
    link: '/useful-links'
  },

  {
    title: 'Rating',
    icon: 'star-outline',
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

