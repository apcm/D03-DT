
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Box;

@Service
@Transactional
public class BoxService {

	//Repository
	@Autowired
	public BoxRepository	boxRepository;


	//Services

	//Constructor
	public BoxService() {
		super();
	}

	//Simple CRUD
	public Box create() {
		return new Box();
	}

	//9.4
	public Box save(final Box box) {
		Assert.notNull(box);

		//Logged user must be a customer/handyworker
		final Authority a = new Authority();
		final Authority b = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.CUSTOMER);
		b.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a) || user.getAuthorities().contains(b));

		//Restrictions
		Assert.notNull(box.getName());
		Assert.notNull(box.getPredefined());
		if (box.getPredefined() == true)
			Assert.isTrue(box.getName() == "in" || box.getName() == "out" || box.getName() == "trash" || box.getName() == "spam");
		final Box res;
		res = this.boxRepository.save(box);
		return res;
	}

	public void delete(final Box box) {
		Assert.notNull(box);
		Assert.notNull(box.getId());
		Assert.isTrue(this.boxRepository.exists(box.getId()));

		//Logged user must be a customer/handyworker
		final Authority a = new Authority();
		final Authority b = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.CUSTOMER);
		b.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a) || user.getAuthorities().contains(b));

		//Restrictions
		Assert.notNull(box.getName());
		Assert.isTrue(box.getPredefined() == false);

		this.boxRepository.delete(box);

	}

	public Collection<Box> findAll() {
		return this.boxRepository.findAll();
	}

	public Box findOne(final Box box) {
		return this.boxRepository.findOne(box.getId());
	}
	
	public Collection<Box> findByUser(final int id) {
		Collection<Box> boxes = null;
		Authority a=new Authority();
		a.setAuthority(Authority.CUSTOMER);
		Authority b=new Authority();
		b.setAuthority(Authority.HANDYWORKER);
		Authority c=new Authority();
		c.setAuthority(Authority.SPONSOR);
		Authority d=new Authority();
		d.setAuthority(Authority.REFEREE);
		Authority e=new Authority();
		e.setAuthority(Authority.ADMIN);
		if(LoginService.getPrincipal().getAuthorities().contains(a)) {
			boxes=this.boxRepository.findBoxByCustomer(id);
		}else if(LoginService.getPrincipal().getAuthorities().contains(b)) {
			boxes=this.boxRepository.findBoxByHw(id);
		}else if(LoginService.getPrincipal().getAuthorities().contains(c)) {
			boxes=this.boxRepository.findBoxBySponsor(id);
		}else if(LoginService.getPrincipal().getAuthorities().contains(d)) {
			boxes=this.boxRepository.findBoxByReferee(id);
		}else if(LoginService.getPrincipal().getAuthorities().contains(e)) {
			boxes=this.boxRepository.findBoxByAdmin(id);
		}
		return boxes;
	}

}
