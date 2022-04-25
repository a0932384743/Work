'use strict';
(function() {
  /* ========= sidebar toggle ======== */
  const SidebarNavWrapper = document.querySelector('.sidebar-nav-wrapper');
  const MainWrapper = document.querySelector('.main-wrapper');
  const MenuToggleButton = document.querySelector('#menu-toggle');
  const MenuToggleButtonIcon = document.querySelector('#menu-toggle svg');
  const Overlay = document.querySelector('.overlay');
  const PerPage = document.querySelector('#PerPage');
  const SearchButton = document.querySelector('#SearchButton');
  const TableHeader = document.querySelector('#TableHeader')

  MenuToggleButton.addEventListener('click', () => {
    SidebarNavWrapper.classList.toggle('active');
    Overlay.classList.add('active');
    MainWrapper.classList.toggle('active');

    if (document.body.clientWidth > 1200) {
      if (SidebarNavWrapper.classList.contains('active')) {
        MenuToggleButtonIcon.innerHTML = '<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>';

      } else {
        MenuToggleButtonIcon.innerHTML = '<path fill-rule="evenodd" d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"></path>';
      }
    } else {
      if (SidebarNavWrapper.classList.contains('active')) {
        MenuToggleButtonIcon.innerHTML = '<path fill-rule="evenodd" d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"></path>';
      }
    }
  });

  Overlay.addEventListener('click', () => {
    SidebarNavWrapper.classList.remove('active');
    Overlay.classList.remove('active');
    MainWrapper.classList.remove('active');
  });


  const tableData = [{
    name: 'jacky',
    desc: 'i am jacky',
    age: 22
  }, {
    name: 'marry',
    desc: 'i am marry',
    age: 33
  }, {
    name: 'jonh',
    desc: 'i am john',
    age: 21
  }, {
    name: 'bady',
    desc: 'i am bady',
    age: 76
  }, {
    name: 'gary',
    desc: 'i am gary',
    age: 33
  }, {
    name: 'alpha',
    desc: 'i am apha',
    age: 3
  }, {
    name: 'alpha',
    desc: 'i am apha',
    age: 3
  }, {
    name: 'alpha',
    desc: 'i am apha',
    age: 3
  }, {
    name: 'alpha6',
    desc: 'i am apha',
    age: 3
  }, {
    name: 'alpha1',
    desc: 'i am apha',
    age: 3
  }, {
    name: 'alpha2',
    desc: 'i am apha',
    age: 3
  }, {
    name: 'alpha3',
    desc: 'i am apha',
    age: 3
  }, {
    name: 'alpha4',
    desc: 'i am apha',
    age: 3
  }, {
    name: 'alpha5',
    desc: 'i am apha',
    age: 3
  }]

  const tableField = [{
    key: 'name',
    label: 'Name'
  }, {
    key: 'desc',
    label: 'Description'
  }, {
    key: 'age',
    label: 'Age'
  }];

  const tdata = new TableData('#TableBody', tableData);
  const pg = new Pagination('#Pagination', tableData.length);

  pg.watch = (page, total) => {
    document.querySelector('#Page').innerHTML = page;
    document.querySelector('#Total').innerHTML = total;
    tdata.page = page;
  };

  pg.perPage = ~~PerPage.value;
  tdata.perPage = ~~PerPage.value;

  PerPage.addEventListener('change', (e) => {
    tdata.perPage = ~~e.target.value
    pg.perPage = ~~e.target.value;
    pg.page = 1;
  });

  SearchButton.addEventListener('click', (e) => {
    tdata.filter = document.querySelector('#Search').value || '';
    pg.total = tdata.dataLength;
    pg.page = 1;
  });

  const tr = document.createElement('tr');
  tr.className = 'bg-primary-900 text-light';

  tableField.forEach((f) => {
    const th = document.createElement('th');
    th.innerHTML = f.label;
    tr.appendChild(th);
    if (f.key === tdata.sort) {
      th.className = 'sort sort-' + tdata.order;
    } else {
      th.className = '';
    }

    th.addEventListener('click', () => {
      document.querySelector('.sort').className = '';

      tdata.order = tdata.order === 'asc' ? 'desc' : 'asc';
      tdata.sort = f.key;

      th.className = 'sort sort-' + tdata.order;
    });

  });

  TableHeader.innerHTML = '';
  TableHeader.appendChild(tr);

  function TableData(ele, data) {
    let _data = data;
    this.ele = document.querySelector(ele);
    let _sort = '';
    let _order = 'desc';
    let _filter = ''
    let _page = 1;
    let _perPage = 10;
    if (data && data.length) {
      _sort = Object.keys(data[0])[0] || '';
    }
    const render = () => {
      this.ele.innerHTML = '';
      this.data.filter(d => {
        let isExist = false;
        for (let prop in d) {

          if ((d[prop] + '').indexOf(this.filter) > -1) {
            isExist = true;
            break;
          }

        }

        return isExist;

      }).sort((a, b) => {
        if (this.order == 'asc') {
          return a[this.sort] > b[this.sort] ? 1 : -1;
        } else {
          return a[this.sort] > b[this.sort] ? -1 : 1;
        }

      }).splice((this.page - 1) * this.perPage, this.page * this.perPage).forEach(d => {
        const tr = document.createElement('tr');
        for (let prop in d) {
          const td = document.createElement('td');
          td.innerHTML = d[prop];
          tr.appendChild(td)
        }
        this.ele.appendChild(tr);
      })
    }


    Object.defineProperty(this, 'data', {
      get: function() {
        return _data;
      },
      set: function(value) {
        _data = value || [];
        render();
      }
    });


    Object.defineProperty(this, 'sort', {
      get: function() {
        return _sort;
      },
      set: function(value) {
        _sort = value || '';
        render();
      }
    });

    Object.defineProperty(this, 'order', {
      get: function() {
        return _order || 'desc';
      },
      set: function(value) {
        _order = value || 'desc';
        render();
      }
    });

    Object.defineProperty(this, 'filter', {
      get: function() {
        return _filter;
      },
      set: function(value) {
        _filter = value || '';
        render();
      }
    });


    Object.defineProperty(this, 'page', {
      get: function() {
        return _page;
      },
      set: function(value) {
        _page = value < 0 ? 1 : value;
        render();
      }
    });

    Object.defineProperty(this, 'perPage', {
      get: function() {
        return _perPage;
      },
      set: function(value) {
        _perPage = value < 0 ? 10 : value;
        render();
      }
    });

    Object.defineProperty(this, 'dataLength', {
      get: function() {
        return (this.data || []).filter(d => {
          let isExist = false;
          for (let prop in d) {

            if ((d[prop] + '').indexOf(this.filter) > -1) {
              isExist = true;
              break;
            }

          }

          return isExist;

        }).length;
      },

    })
  }


  function Pagination(ele, total) {
    let _total = total;
    let _perPage = 10;
    let _step = 5;
    let _page = 1;

    this.ele = document.querySelector(ele);

    let _watch = function() {

    }

    const render = () => {

      this.ele.innerHTML = '';

      const startLi = document.createElement('li');
      startLi.className = 'page-item';
      startLi.innerHTML = '<a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true"9>&laquo;</span></a>';
      startLi.addEventListener('click', () => {
        this.ele.children[this.page].classList.remove('active');
        this.page = 1;
        this.ele.children[this.page].classList.add('active');
      });
      this.ele.appendChild(startLi);

      for (let i = 0; i < this.total; i++) {
        const li = document.createElement('li');
        li.className = 'page-item page-' + (i + 1);
        li.innerHTML = '<a class="page-link" href="#">' + (i + 1) + '</a>';
        li.addEventListener('click', () => {
          this.ele.children[this.page].classList.remove('active');
          this.page = (i + 1);
          this.ele.children[this.page].classList.add('active');
        });
        this.ele.appendChild(li);

      }
      const endLi = document.createElement('li');
      endLi.className = 'page-item';
      endLi.innerHTML = '<a class="page-link" href="#" aria-label="Next"><span aria-hidden="true"9>&raquo;</span></a>';
      endLi.addEventListener('click', () => {
        this.ele.children[this.page].classList.remove('active');
        this.page = this.total;
        this.ele.children[this.page].classList.add('active');
      });
      this.ele.appendChild(endLi);

      this.ele.children[this.page].classList.add('active');

    }


    Object.defineProperty(this, 'total', {
      get: function() {
        return Math.ceil(_total / this.perPage) || 1;
      },
      set: function(value) {
        _total = value;
        render();
      }
    });

    Object.defineProperty(this, 'perPage', {
      get: function() {
        return _perPage;
      },
      set: function(value) {
        _perPage = value;
        render();
      }
    });

    Object.defineProperty(this, 'step', {
      get: function() {
        return _step;
      },
      set: function(value) {
        _step = value;
        render();
      }
    });

    Object.defineProperty(this, 'watch', {
      get: function() {
        return _watch;
      },
      set: function(value) {
        if (value && typeof value == 'function') {
          _watch = value;
          _watch(this.page, this.total);
        } else {
          _watch = function() {};
        }
      }
    });


    Object.defineProperty(this, 'page', {
      get: function() {
        return _page;
      },
      set: function(value) {
        _page = value;
        if (this.watch && typeof this.watch == 'function') {
          this.watch(value, this.total);
        }
      }
    });

    render();

  }
})();
